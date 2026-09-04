inherit kernel

SECTION = "kernel"
SUMMARY = "Stable Linux tree with patches for lucky7"
HOMEPAGE = "https://kernel.org"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
COMPATIBLE_MACHINE = "lucky7"

SRC_URI = "git://git@github.com/Aiden-Isik/linux.git;protocol=https;branch=lucky7-mainline-7.2 \
           file://defconfig \
           file://uniloader_defconfig"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"
B = "${S}"

# Override uniLoader's defconfig
MACHINE_DEFCONFIG = "uniloader_defconfig"

do_configure_uniloader:prepend() {
    cp "${UNPACKDIR}/${MACHINE_DEFCONFIG}" "${B}/uniloader/configs"
}

inherit uniloader mkbootimg
