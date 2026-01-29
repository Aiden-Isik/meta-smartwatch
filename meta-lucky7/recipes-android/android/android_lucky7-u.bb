inherit gettext

SUMMARY = "Downloads the Samsung Galaxy Watch 4/FE /system, /vendor, /include and /usr/libexec/hal-droid folders, and installs them for libhybris"
LICENSE = "CLOSED"
SRC_URI = "https://files.aidenisik.scot/asteroidos/hybris-upsidedowncake-armv7neon.tar.gz;name=hybris \
    https://files.aidenisik.scot/asteroidos/system-lucky7-u.tar.gz;name=system \
"
SRC_URI[hybris.md5sum] = "3ccda59da590caa6056fcd3fd5a72c71"
SRC_URI[hybris.sha256sum] = "dd53865776c61194dc47566948156b2b27cf6cfe846aec40c2506095a7038a93"
SRC_URI[system.md5sum] = "94cf0ca4aadd3b273a110d08e49c0040"
SRC_URI[system.sha256sum] = "044f1e8d384b7df32037787d9a61ee40629b67aa03e95f60be6509c3cb46aec1"
PV = "upsidedowncake"

PACKAGE_ARCH = "${MACHINE_ARCH}"
INHIBIT_PACKAGE_STRIP = "1"
COMPATIBLE_MACHINE = "lucky7"
INSANE_SKIP:${PN} = "already-stripped"
S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"
B = "${S}"

PROVIDES += "virtual/android-system-image"
PROVIDES += "virtual/android-headers"

do_install() {
    install -d ${D}/system/
    cp -r system/* ${D}/system/

    install -d ${D}/vendor/
    cp -r vendor/* ${D}/vendor/

    install -d ${D}/usr/
    cp -r usr/* ${D}/usr/

    install -d ${D}${includedir}/android
    cp -r include/* ${D}${includedir}/android/

    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${D}${includedir}/android/android-headers.pc ${D}${libdir}/pkgconfig
    rm ${D}${includedir}/android/android-headers.pc
}

do_package_qa() {
}

PACKAGES =+ "android-system android-headers"
FILES:android-system = "/system /vendor /usr"
FILES:android-headers = "${libdir}/pkgconfig ${includedir}/android"
EXCLUDE_FROM_SHLIBS = "1"
