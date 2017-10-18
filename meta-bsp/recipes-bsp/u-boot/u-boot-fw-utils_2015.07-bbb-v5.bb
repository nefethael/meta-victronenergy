require u-boot-common_${PV}.inc
require u-boot-fw-utils.inc

do_compile () {
	oe_runmake -C ${S} \
		CC="${CC} ${CFLAGS} ${LDFLAGS}" \
		HOSTCC="${BUILD_CC} ${BUILD_CFLAGS} ${BUILD_LDFLAGS}" \
		env
}
