FILESEXTRAPATHS:prepend:lucky7 := "${THISDIR}/files:"

SRC_URI:append:lucky7 = " file://primaryuse.conf "

do_install:append:lucky7() {
    cp ${UNPACKDIR}/primaryuse.conf ${D}/etc/sensorfw/primaryuse.conf
}
