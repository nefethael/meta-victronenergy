require u-boot-common_${PV}.inc
require u-boot-fw-utils.inc

SRC_URI += " \
	file://u-boot.env \
"

LIBNAME = "libubootenv.a"

do_compile () {
	oe_runmake -C ${S} HOSTCC="${CC} ${CFLAGS} ${LDFLAGS}" HOSTSTRIP="echo" env
}

do_install_append () {
	mkdir -p ${D}${datadir}/u-boot
	install ${WORKDIR}/u-boot.env ${D}${datadir}/u-boot
}
