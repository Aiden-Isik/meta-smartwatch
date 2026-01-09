FILESEXTRAPATHS:prepend:lucky7 := "${THISDIR}/mce:"
SRC_URI:append:lucky7 = " file://20als-defaults.ini"

do_install:append:lucky7() {
    cp ${UNPACKDIR}/20als-defaults.ini ${D}/etc/mce/20als-defaults.ini
}
