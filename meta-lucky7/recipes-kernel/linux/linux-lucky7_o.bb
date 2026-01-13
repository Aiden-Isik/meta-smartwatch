require recipes-kernel/linux/linux.inc
inherit gettext

SECTION = "kernel"
SUMMARY = "Android kernel for the Samsung Galaxy Watch FE"
HOMEPAGE = "https://github.com/Aiden-Isik/linux-android-lucky7"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
COMPATIBLE_MACHINE = "lucky7"

# Use an older version of gcc (gcc >= 9 doesn't boot.)
inherit kernel-gcc8

SRC_URI = "git://github.com/Aiden-Isik/linux-android-lucky7;branch=android13-5.15;protocol=https \
           file://defconfig \
           file://img_info \
           "
SRCREV = "5b5176b536f9318dd5362b3ba387944b0d087f6a"
LINUX_VERSION ?= "5.15"
PV = "${LINUX_VERSION}+tiramisu"
S = "${WORKDIR}/git"
B = "${S}"

do_configure:prepend() {
    install -m 644 -D ${UNPACKDIR}/defconfig ${WORKDIR}/defconfig
}

do_install:append() {
    rm -rf ${D}/usr/src/usr/
}

inherit mkboot old-kernel-gcc-hdrs
