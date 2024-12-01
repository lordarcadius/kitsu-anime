# General ProGuard Rules
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn kotlin.reflect.jvm.internal.**

# Retrofit-specific Rules
-keepattributes Signature
-keepattributes Exceptions

# Retrofit annotations
-keep class retrofit2.** { *; }
-keepattributes RuntimeVisibleAnnotations

# OkHttp
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**

# Gson rules
-keep class com.google.gson.** { *; }
-keepattributes *Annotation*
-dontwarn com.google.gson.**

# Prevent obfuscation of fields used for Gson serialization/deserialization
-keepclassmembers class com.vipuljha.kitsuanime.features.anime.domain.models.** {
    <fields>;
    <methods>;
}

-keepclassmembers class com.vipuljha.kitsuanime.features.anime.data.dto.** {
    <fields>;
    <methods>;
}

# Keep all fields annotated with @SerializedName
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Ensure enums are preserved
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep Retrofit API interfaces
-keep interface com.vipuljha.kitsuanime.** { *; }

# Prevent obfuscation of methods annotated with Retrofit annotations
-keepclassmembers class * {
    @retrofit2.http.* <methods>;
}