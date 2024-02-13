# bin/bash!

SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
FILES=$(find "${SCRIPT_DIR}/../docs/img/" -name '*.JPG' -or -name '*.jpg' -or -name '*.png')

for f in $FILES
do
  if [[ "$f" == *_LQIP* ]] # * is used for pattern matching
  then
    echo "ignoring $f";
  else
    convert "$f" -resize 64x64  "${f:0:-4}_LQIP.jpg";
  fi

#  convert "$f" -resize 64x64  "${f:0:-4}_LQIP.png"
done
