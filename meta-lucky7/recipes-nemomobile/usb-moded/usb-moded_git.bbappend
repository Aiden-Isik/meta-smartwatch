FILESEXTRAPATHS:prepend:lucky7 := "${THISDIR}/usb-moded:"

SRC_URI:append:lucky7 = " file://usb-moded.ini "

do_install:append:lucky7() {
    mkdir -p ${D}/var/lib/usb-moded/
    cp ${UNPACKDIR}/usb-moded.ini ${D}/var/lib/usb-moded/usb-moded.ini
}
