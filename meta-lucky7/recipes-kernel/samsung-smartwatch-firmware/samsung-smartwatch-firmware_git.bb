SECTION = "kernel"
SUMMARY = "Firmware for Samsung lucky7 smartwatch"
HOMEPAGE = "https://github.com/casept/linux-samsung-smartwatch-firmware"
LICENSE = "CLOSED"
COMPATIBLE_MACHINE = "lucky7"

SRC_URI = " git://git@github.com/casept/linux-samsung-smartwatch-firmware.git;protocol=https;branch=master "

SRC_URI[sha256sum] = "fffffffl33857fl2fa0318a1fac1e788941015bf39894a255b8323c5138037c6"
SRCREV = "fe84f346a9337b5ad4a474895e1a62c52aad7f36"
PV = "master"
S = "${WORKDIR}/git"

FILES:${PN} += " /usr/lib/firmware "

do_install() {
        install -m 0755 -d ${D}/usr/lib/firmware/
        # Touch screen
        install -m 0755 -d ${D}/usr/lib/firmware/tsp_melfas/w/
        install -m 0644 ${S}/lucky7/tsp_melfas/w/* ${D}/usr/lib/firmware/tsp_melfas/w/
        # BT / Wi-Fi
        install -m 0755 -d ${D}/usr/lib/firmware/brcm/
        cp ${S}/lucky7/brcm/bcm4334W.hcd ${D}/usr/lib/firmware/brcm/BCM.hcd
        cp ${S}/lucky7/brcm/brcmfmac43342-sdio.bin ${D}/usr/lib/firmware/brcm/brcmfmac43342-sdio.samsung,lucky7.bin
        cp ${S}/lucky7/brcm/brcmfmac43342-sdio.txt ${D}/usr/lib/firmware/brcm/brcmfmac43342-sdio.txt
        # Misc. accelerators / codecs
        install -m 0644 ${S}/lucky7/s5p* ${D}/usr/lib/firmware/
        # Sensor hub
        install -m 0644 ${S}/lucky7/ssp* ${D}/usr/lib/firmware/
}
