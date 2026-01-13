FILESEXTRAPATHS:prepend:lucky7 := "${THISDIR}/${PN}:"

SRC_URI:append:lucky7 = " file://nonplat_property_contexts \
    file://plat_property_contexts"

do_install:append:lucky7() {
    install -m 0644 ${UNPACKDIR}/nonplat* ${D}/
    install -m 0644 ${UNPACKDIR}/plat* ${D}/
}

FILES:${PN}:append:lucky7 = " /nonplat* /plat*"
