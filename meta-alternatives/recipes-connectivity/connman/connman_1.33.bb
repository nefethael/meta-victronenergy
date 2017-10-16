require connman.inc

SRC_URI  = "${KERNELORG_MIRROR}/linux/network/${BPN}/${BP}.tar.xz \
            file://connman \
            file://0003-stats-Fix-bad-file-descriptor-initialisation.patch \
            file://CVE-2017-12865.patch \
            "
SRC_URI[md5sum] = "c51903fd3e7a6a371d12ac5d72a1fa01"
SRC_URI[sha256sum] = "bc8946036fa70124d663136f9f6b6238d897ca482782df907b07a428b09df5a0"
