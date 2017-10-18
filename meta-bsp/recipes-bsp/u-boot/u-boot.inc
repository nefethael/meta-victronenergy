require ${COREBASE}/meta/recipes-bsp/u-boot/u-boot.inc

DEPENDS += "u-boot-mkimage-native"

UBOOT_LOCALVERSION = "-venus"

SRC_URI += "file://install.cmds"

UBOOT_SCRIPTS = "install:install-${MACHINE}"

do_compile_append () {
	for scr in ${UBOOT_SCRIPTS}; do
		src=${scr%:*}
		dst=${scr#*:}
		mkimage -A arm -T script -C none \
			-d ${WORKDIR}/${src}.cmds ${WORKDIR}/${dst}.scr
	done
}

do_deploy_append () {
	install -d ${DEPLOYDIR}
	for scr in ${UBOOT_SCRIPTS}; do
		dst=${scr#*:}
		install -m 0644 ${WORKDIR}/${dst}.scr ${DEPLOYDIR}
	done
}
