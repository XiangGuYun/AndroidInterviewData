#include <jni.h>
#include <string>
#include <android/log.h>
using namespace std;

#define LOG_TAG  "Test"
#define LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)

extern "C" JNIEXPORT jstring JNICALL
Java_com_yxd_devlib_ndk_NativeFragment_stringFromJNI(JNIEnv *env, jobject thiz) {
    LOGD("666");
    string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}