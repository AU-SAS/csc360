#!/bin/sh
# Starts ImageJ 1.x with the Java installed on this machine, in place of the
# bundled launcher. The script belongs in the ImageJ installation directory,
# or IJ_HOME names that directory. IJ_MEMORY overrides the heap size.
# The reasoning is recorded in docs/imagej.md.

set -u

# The script's own directory, found by stripping the last path component rather
# than by calling dirname, so that the script depends on the shell alone.
if [ -z "${IJ_HOME:-}" ]; then
    case $0 in
        */*) IJ_HOME=$(CDPATH= cd -- "${0%/*}" && pwd) ;;
        *) IJ_HOME=$(pwd) ;;
    esac
fi
IJ_MEMORY=${IJ_MEMORY:-2048m}

log() {
    printf 'imagej: %s\n' "$1" >&2
}

if [ -n "${JAVA_HOME:-}" ] && [ -x "$JAVA_HOME/bin/java" ]; then
    JAVA=$JAVA_HOME/bin/java
elif command -v java > /dev/null 2>&1; then
    JAVA=$(command -v java)
else
    log 'no Java was found, so ImageJ cannot be started.'
    log "JAVA_HOME is ${JAVA_HOME:-unset}, and no java is on PATH."
    log "PATH is $PATH"
    log 'Install a JDK (8 or later) or set JAVA_HOME to one, then run this script again.'
    exit 1
fi

if [ ! -f "$IJ_HOME/ij.jar" ]; then
    log "ij.jar was not found in $IJ_HOME, so there is nothing to start."
    log 'Place this script in the ImageJ installation directory, or set IJ_HOME to it.'
    exit 1
fi

# Only the X and Wayland platforms announce a display this way; Windows and
# macOS have one without saying so, and would be warned at every start.
case $(uname -s) in
    Linux* | *BSD* | SunOS*)
        if [ -z "${DISPLAY:-}" ] && [ -z "${WAYLAND_DISPLAY:-}" ]; then
            log 'neither DISPLAY nor WAYLAND_DISPLAY is set.'
            log 'ImageJ is a desktop application and fails on a host without a display; a'
            log 'virtual one serves, as in: xvfb-run sh imagej.sh -batch macro.ijm'
        fi
        ;;
esac

cd "$IJ_HOME" || exit 1
exec "$JAVA" "-Xmx$IJ_MEMORY" "-Dplugins.dir=$IJ_HOME" -cp "$IJ_HOME/ij.jar" ij.ImageJ "$@"
