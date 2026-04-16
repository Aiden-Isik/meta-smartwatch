INSANE_SKIP:${PN} += "installed-vs-shipped"

do_install:append:lucky7() {
    install -d ${D}/usr/share/
    install -m 0755 ${UNPACKDIR}/psplash-img-320.png ${D}/usr/share/psplash.png
}
