BRANCH ?= "${MACHINE}"

SRC_URI = "git://github.com/victronenergy/u-boot.git;protocol=https;branch=${BRANCH}"

S = "${WORKDIR}/git"
