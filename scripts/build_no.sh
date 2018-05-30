#!/usr/bin/env sh

#
# This script manages app build numbers.
# It returns the next build number to be used.
# If ran with --tag it will mark current HEAD with new build number.
#
# These numbers are used to mark app artifacts for:
# * Play Store - versionCode attribute (gradle)
# * Apple Store - CFBundleVersion attribute (plutil)
#
# The numbers need to be incremeneted and are maintained via
# git tags matching the '^build-[0-9]+$' regex.
# Builds from an already tagged commit should use the same number.
#
# For more details see:
# * https://developer.android.com/studio/publish/versioning
# * https://developer.apple.com/library/content/documentation/General/Reference/InfoPlistKeyReference/Articles/CoreFoundationKeys.html
# 

echo "$BUILD_NUMBER"
