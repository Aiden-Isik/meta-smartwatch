inherit kernel

SECTION = "kernel"
SUMMARY = "Stable Linux tree with patches for lucky7"
HOMEPAGE = "https://kernel.org"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
COMPATIBLE_MACHINE = "lucky7"

SRC_URI = "git://git@github.com/Aiden-Isik/linux.git;protocol=https;branch=lucky7-mainline"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"
B = "${S}"

inherit mkbootimg
