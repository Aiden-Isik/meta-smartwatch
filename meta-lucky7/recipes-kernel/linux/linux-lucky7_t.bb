require recipes-kernel/linux/linux.inc
inherit gettext

SECTION = "kernel"
SUMMARY = "Android kernel for the Samsung Galaxy Watch 4/FE"
HOMEPAGE = "https://github.com/Aiden-Isik/linux-android-lucky7"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
COMPATIBLE_MACHINE = "kernel-lucky7"

# Set the required environment variables to correctly build the kernel
export PLATFORM_VERSION
PLATFORM_VERSION = "14"
export ANDROID_MAJOR_VERSION
ANDROID_MAJOR_VERSION = "u"
export TARGET_SOC
TARGET_SOC = "s5e5515"
export ARCH
ARCH = "arm64"

DEPENDS += "rsync-native"

SRC_URI = "git://github.com/Aiden-Isik/linux-android-lucky7;branch=android13-5.15;protocol=https \
           file://defconfig \
           file://img_info \
           "
SRCREV = "05f7ccb07fdedffca6826bdc6b7555d6e2d3d502"
LINUX_VERSION ?= "5.15"
PV = "${LINUX_VERSION}+tiramisu"
S = "${WORKDIR}/git"
B = "${S}"

do_configure:prepend() {
    install -m 644 -D ${UNPACKDIR}/defconfig ${WORKDIR}/defconfig
    install -m 644 ${UNPACKDIR}/s5e5515_fe.dtb ${S}/arch/arm64/boot/dts/${KERNEL_DEVICETREE}
}

do_install:append() {
    rm -rf ${D}/usr/src/usr/
}

inherit mkbootimg