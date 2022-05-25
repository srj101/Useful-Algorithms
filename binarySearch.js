function binarySearch(arr, item) {
  let startIndex = 0;
  let endIndex = arr.length - 1;

  while (startIndex < endIndex) {
    let middleIndex = Math.floor((startIndex + endIndex) / 2);

    if (arr[middleIndex] === item) {
      return `Found at index ${middleIndex}`;
    }

    if (arr[middleIndex] < item) {
      startIndex = middleIndex + 1;
    } else {
      endIndex = middleIndex - 1;
    }
  }

  return "Not Found";
}

console.log(binarySearch([5, 10, 20, 30, 40], 30));
