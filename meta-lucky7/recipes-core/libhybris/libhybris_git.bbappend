# native_handle_clone is not declared on android-headers provider for `lucky7`
CFLAGS:append:lucky7 = " -Wno-implicit-function-declaration -Wno-int-conversion"