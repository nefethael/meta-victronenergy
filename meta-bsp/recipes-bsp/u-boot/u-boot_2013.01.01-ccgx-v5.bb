require u-boot-common_${PV}.inc
require u-boot.inc

SRC_URI += " \
	file://live.cmds \
	file://upgrade.cmds \
	file://splash.bgra \
"

UBOOT_SCRIPTS += "live upgrade"

do_deploy_append () {
	install ${WORKDIR}/splash.bgra ${DEPLOYDIR}
}
