# this little script generates keystores for debug, release and upload signing
# it requires following tools to be installes on your machine in order to run:
# - openssl - keytool -
#
# the script will ask for certificate details and prints generated passwords in console output
# the generated keystores will be saved in the same directory it is beein executed from.
function generatePassword(){
    local __password=$(openssl rand -base64 128)
    echo "$__password"
}

function generateKeystore(){
  local __keystoreName=%1
  local __alias=%2
  local __storePass=%3
  local __keyPass=%4
  local __certificate=%5

  keytool -genkeypair -alias "$__alias" -keyalg RSA -validity 10950 -keystore "$__keystoreName" -storetype JKS -keypass "$__keyPass" -storepass "$__storePass" -dname "$__certificate"
}

function getUserCertificate(){
  read -p "CN (Common Name): " __CN
  read -p "ON (organizationUnit):" __OU
  read -p "O (organizationName):" __O
  read -p "L (localityName):" __L
  read -p "S (stateName):" __S
  read -p "C (country):" __C

  echo "CN=$__CN,OU=$__OU, O=$__O, L=$__L, S=$__S, C=$__C"
}

echo "Please provide certificate details (press enter so skip)"
certificate=$(getUserCertificate)
echo "$certificate"

echo "================================================"

debugKeyStoreFile="debug.keystore"
debugAlias="androiddebugkey"
debugStorePass="android"
debugKeyPass="android"

generateKeystore "$debugKeyStoreFile" "$debugAlias" "$debugStorePass" "$debugKeyPass" "$certificate"

echo "Debug StorePass: $debugStorePass"
echo "Debug KeyPass: $debugKeyPass"

echo "================================================"

releaseKeyStoreFile="release"
releaseAlias="release-key"
releaseStorePass=$(generatePassword)
releaseKeyPass=$(generatePassword)

generateKeystore "$releaseKeyStoreFile" "$releaseAlias" "$releaseStorePass" "$releaseKeyPass" "$certificate"

echo "Release StorePass: $releaseStorePass"
echo "Release KeyPass: $releaseKeyPass"

echo "================================================"

uploadKeyStoreFile="upload.keystore"
uploadAlias="upload-key"
uploadStorePass=$(generatePassword)
uploadKeyPass=$(generatePassword)

generateKeystore "$uploadKeyStoreFile" "$uploadAlias" "$uploadStorePass" "$uploadKeyPass" "$certificate"

echo "Upload StorePass: $uploadStorePass"
echo "Upload KeyPass: $uploadKeyPass"
