# Compiling all components of the kernel for these watches is a somewhat complicated ordeal.
#
# The Samsung vendor kernel compiles, but does not boot even after extensive debugging.
# The Android Common Kernel (ACK) compiles and boots, but does not have the Samsung drivers.
#
# The solution to this predicament is to build the core kernel from the ACK tree,
# and build the drivers from the Samsung tree.
#
# Embedding the Samsung drivers directly into the ACK is not feasible, they rely heavily on modifications
# to the core kernel code in the tree and the time that would be spent making that work is probably
# better spent mainlining.

require recipes-kernel/linux/linux.inc
inherit gettext

SECTION = "kernel"
SUMMARY = "Android kernel for the Samsung Galaxy Watch 4/FE"
HOMEPAGE = "https://github.com/Aiden-Isik/linux-android-lucky7"
LICENSE = "GPL-2.0-only"
COMPATIBLE_MACHINE = "kernel-lucky7"

DEPENDS += "rsync-native"

SRC_URI = "git://android.googlesource.com/kernel/common;branch=android13-5.15;protocol=https;name=ack \
           git://github.com/Aiden-Isik/linux-android-lucky7;branch=android13-5.15;protocol=https;name=vendor;destsuffix=git/vendor \
           file://defconfig \
           file://img_info \
           file://s5e5515_fe.dtb"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
LIC_FILES_CHKSUM_vendor = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

SRCREV_ack = "9b493559553bf33903a81ec85f91f085357b69e2"
SRCREV_vendor = "e304246f56c550cda2e0f770372b056b05ff7ad4"
SRCREV_FORMAT = "ack_vendor"

LINUX_VERSION ?= "5.15"
PV = "${LINUX_VERSION}+tiramisu"
S = "${WORKDIR}/git"
B = "${S}"

# Environment variables required for the Samsung source tree
ARCH = "arm64"
PLATFORM_VERSION = "14"

do_configure:prepend() {
    install -m 644 -D ${UNPACKDIR}/defconfig ${WORKDIR}/defconfig
}

# Switch to the vendor source tree and generate the .config used to generate the modules
do_compile_kernelmodules:prepend() {
    OLD_S=${S}
    OLD_B=${B}
    S=${S}/vendor
    B=${B}/vendor

    oe_runmake -C ${B} ${PARALLEL_MAKE} s5e5515-lucky7usue_defconfig ${KERNEL_EXTRA_ARGS}
}

# Switch back to the Common Kernel tree after copying the compiled modules back
do_compile_kernelmodules:append() {
    # Copy the modules list and modules to the main tree so the modules_install target works
    cp ${B}/modules.order ${OLD_B}/modules.order

    for i in $(cat ${B}/modules.order); do
        mkdir -p ${OLD_B}/$(dirname ${i})
        cp ${B}/${i} ${OLD_B}/${i}
    done

    S="$OLD_S"
    B="$OLD_B"
}

do_install:append() {
    rm -rf ${D}/usr/src/usr/
    cp ${UNPACKDIR}/s5e5515_fe.dtb ${S}/arch/arm64/boot/dts/${KERNEL_DEVICETREE}
}

inherit mkbootimg
