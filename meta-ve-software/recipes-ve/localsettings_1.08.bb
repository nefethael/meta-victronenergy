DESCRIPTION = "Localsettings python scripts"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit allarch
inherit ve_package
inherit daemontools

SRC_URI = " \
	gitsm://github.com/victronenergy/localsettings.git;protocol=https;tag=v${PV} \
	file://set_setting.sh \
	file://com.victronenergy.settings.conf \
	file://convert_setting_canbus_profile.py \
"
S = "${WORKDIR}/git"

DAEMONTOOLS_SERVICE_DIR = "${bindir}/service"
DAEMONTOOLS_RUN = "softlimit -d 100000000 -s 1000000 -a 100000000 ${bindir}/${PN}.py"

RDEPENDS_${PN} += " \
	python-dbus \
	python-lxml \
"

do_install () {
	install -d ${D}${bindir}
	install -m 755 -D ${S}/*.py ${D}${bindir}
	install -m 755 -D ${WORKDIR}/set_setting.sh ${D}${bindir}

	install -d ${D}${bindir}/ext/velib_python
	install -m 644 ${S}/ext/velib_python/tracing.py ${D}${bindir}/ext/velib_python

	install -d ${D}/${sysconfdir}/dbus-1/system.d
	install -m 644 ${WORKDIR}/com.victronenergy.settings.conf ${D}/${sysconfdir}/dbus-1/system.d

	install -m 755 ${WORKDIR}/convert_setting_canbus_profile.py ${D}${bindir}
}

pkg_postinst_${PN}_prepend () {
	if [ x"$D" = "x" ]; then
		${bindir}/convert_setting_canbus_profile.py
	else
		exit 1
	fi
}

