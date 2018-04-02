FROM openjdk:8

# install Node JS (https://github.com/nodejs/docker-node/blob/90d5e3df903b830d039d3fe8f30e3a62395db37e/7.5/Dockerfile)

RUN groupadd --gid 1000 node \
  && useradd --uid 1000 --gid node --shell /bin/bash --create-home node

ENV NPM_CONFIG_LOGLEVEL info
# that's the only version that doesn't hang on installing react-native-http inside docker
ENV NODE_VERSION 8.9.1

RUN curl -SLO "https://nodejs.org/dist/v$NODE_VERSION/node-v$NODE_VERSION-linux-x64.tar.xz" \
  && tar -xJf "node-v$NODE_VERSION-linux-x64.tar.xz" -C /usr/local --strip-components=1 \
  && rm "node-v$NODE_VERSION-linux-x64.tar.xz" \
  && ln -s /usr/local/bin/node /usr/local/bin/nodejs

# END install Node JS

# install Android SDK (based on: https://github.com/gfx/docker-android-project/blob/master/Dockerfile)

ENV DEBIAN_FRONTEND noninteractive

# Install dependencies
RUN apt-get update && \
    apt-get install -yq libc6 libstdc++6 zlib1g libncurses5 make file --no-install-recommends && \
    apt-get clean

#RUN dpkg --add-architecture i386 && \
#    apt-get update && \
#    apt-get install -yq libc6:i386 libstdc++6:i386 zlib1g:i386 libncurses5:i386 make --no-install-recommends && \
#    apt-get clean

# Download and untar SDK
ENV ANDROID_SDK_URL https://dl.google.com/android/repository/tools_r25.2.3-linux.zip
RUN curl -L "${ANDROID_SDK_URL}" -o /tmp/android-sdk-linux.zip
RUN unzip /tmp/android-sdk-linux.zip -d /usr/local/
RUN rm /tmp/android-sdk-linux.zip
RUN mkdir /usr/local/android-sdk-linux
RUN mv /usr/local/tools /usr/local/android-sdk-linux/
RUN ls /usr/local/android-sdk-linux
RUN ls /usr/local/android-sdk-linux/tools
ENV ANDROID_HOME /usr/local/android-sdk-linux
ENV ANDROID_SDK /usr/local/android-sdk-linux
ENV ANDROID_NDK_HOME /usr/local/android-sdk-linux/ndk-bundle/build
ENV PATH ${ANDROID_HOME}/tools:$ANDROID_HOME/platform-tools:$PATH

# Install Android SDK components
# Get versions from https://developer.android.com/studio/releases/platform-tools.html
# Or in `/usr/local/android-sdk-linux/tools/bin/sdkmanager --list` (or just `sdkmanager --list` on your laptop).
# So, here is the thing - app depends on different Android components, but there is no (obvious to me) way
# to get this list before you try to compile/build the app. This list of versions is crafted manually, analyzing
# errors output of gradle. If new component requirements will be added, this list needs to be updated manually.
ENV ANDROID_COMPONENTS platform-tools,android-27,android-26,android-25,android-24,build-tools-27.0.3,build-tools-26.0.3,build-tools-25.0.3,build-tools-24.03
ENV GOOGLE_COMPONENTS extra-android-m2repository,extra-google-m2repository,extra-google-google_play_services,extra-google-gcm

RUN echo y | android update sdk --no-ui --all --filter "${ANDROID_COMPONENTS}" ; \
    echo y | android update sdk --no-ui --all --filter "${GOOGLE_COMPONENTS}"

RUN echo y | /usr/local/android-sdk-linux/tools/bin/sdkmanager --update

# If you're unlucky person that tries to undestand the whole dependencies setup process, here is
# an explanation: after installing build-tools > 25, `android` command becomes deprecated, changes its command
# line options (they change it every relese, because fuck you developers' experience), so now we need
# use a different command to continue installation of Android SDK/NDK components.
RUN /usr/local/android-sdk-linux/tools/bin/sdkmanager --install ndk-bundle

# Support Gradle
ENV TERM dumb

# Install Lein
# Check out latest releases from https://github.com/technomancy/leiningen/releases
ENV LEIN_VERSION=2.8.1
ENV LEIN_INSTALL=/usr/local/bin/

RUN mkdir -p $LEIN_INSTALL \
  && wget -q https://raw.githubusercontent.com/technomancy/leiningen/$LEIN_VERSION/bin/lein-pkg \
  && mv lein-pkg $LEIN_INSTALL/lein \
  && chmod 0755 $LEIN_INSTALL/lein \
  && wget -q https://github.com/technomancy/leiningen/releases/download/$LEIN_VERSION/leiningen-$LEIN_VERSION-standalone.zip \
  && mkdir -p /usr/share/java \
  && mv leiningen-$LEIN_VERSION-standalone.zip /usr/share/java/leiningen-$LEIN_VERSION-standalone.jar

# Install React-Native
RUN npm install -g react-native-cli

# Install Maven
RUN apt-get install -y maven

## Install dependencies

# Add files needed for installing dependencies
# Use directory /build for that purpose
ADD ./project.clj /build/project.clj

RUN test -f /build/project.clj || (echo "Can't find project.clj. Did you mount your status-react directory to /workspace?"; exit 1;)
RUN cd /build && lein deps

ADD ./package.json ./package-lock.json /build/
RUN cd /build && npm install
#RUN cd /build && ln -sf './node_modules/re-natal/index.js' './re-natal'
#RUN cd /build && ./re-natal deps && ./re-natal use-figwheel && lein re-frisk use-re-natal && ./re-natal enable-source-maps
#RUN cd /build && mvn -f modules/react-native-status/ios/RCTStatus dependency:unpack

# END install Android SDK
