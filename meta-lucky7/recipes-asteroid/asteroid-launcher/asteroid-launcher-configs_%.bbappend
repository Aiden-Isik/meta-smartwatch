FILESEXTRAPATHS:prepend:lucky7 := "${THISDIR}/asteroid-launcher-configs:"
SRC_URI:append:lucky7 = " file://kms-lucky7.json "

do_install:append:lucky7() {
        install -m 0644 ${UNPACKDIR}/kms-lucky7.json ${D}/var/lib/environment/compositor/
}
