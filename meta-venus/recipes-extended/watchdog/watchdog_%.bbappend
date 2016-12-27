inherit update-rc.d

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
PACKAGES =+ "${PN}-norearm"
RDEPENDS_${PN} = "${PN}-norearm"

SRC_URI += "\
	file://get_boot_type.c \
	file://init \
	file://watchdog.conf \
	file://stop_rearming_watchdog.sh \
	file://store_watchdog_error.sh \
	file://0001-Use-MemAvailable-instead-of-MemFree.patch \
"

# note runlevels 0, 1 stop/disable the watchdog
INITSCRIPT_PACKAGES = "${PN} ${PN}-norearm"
INITSCRIPT_NAME_${PN} = "watchdog"
INITSCRIPT_PARAMS_${PN} = "start 80 S . stop 20 0 1 ."

# during unmounting/rebooting the watchdog is no longer rearmed.
# to catch kernel problems during this stage. This needs some help
# from S20_sendsignals to not kill the watchdog process.
INITSCRIPT_NAME_${PN}-norearm = "stop_rearming_watchdog.sh"
INITSCRIPT_PARAMS_${PN}-norearm = "start 15 6 ."

do_compile_append () {
	${CC} ${WORKDIR}/get_boot_type.c -o get_boot_type
}

do_install_append () {
	install -d ${D}${sysconfdir}/init.d ${D}${sbindir}
	install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/watchdog
	install -m 0755 ${WORKDIR}/stop_rearming_watchdog.sh ${D}${sysconfdir}/init.d/stop_rearming_watchdog.sh
	install -m 0755 ${B}/get_boot_type ${D}${sbindir}
	install -m 0755 ${WORKDIR}/store_watchdog_error.sh ${D}${sbindir}
	install -m 0644 ${WORKDIR}/watchdog.conf ${D}${sysconfdir}
}

FILES_${PN}-norearm = "${sysconfdir}/init.d/stop_rearming_watchdog.sh"