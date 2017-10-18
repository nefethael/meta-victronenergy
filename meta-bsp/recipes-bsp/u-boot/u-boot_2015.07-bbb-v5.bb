require u-boot-common_${PV}.inc
require u-boot.inc

UBOOT_ENV = "uEnv"
SRC_URI += "file://uEnv.txt"
