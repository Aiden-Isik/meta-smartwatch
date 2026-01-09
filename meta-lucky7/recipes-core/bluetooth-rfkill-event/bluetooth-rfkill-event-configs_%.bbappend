FILESEXTRAPATHS:prepend:lucky7 := "${THISDIR}/bluetooth-rfkill-event-configs:"
SRC_URI:append:lucky7 = " file://bcm4343.conf \
    file://bluetooth-rfkill-event"
