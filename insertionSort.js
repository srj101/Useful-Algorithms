function insertionSort(arr) {
  const len = arr.length;

  for (let i = 0; i < len; i++) {
    let key = arr[i];
    let j = i - 1;

    while (j >= 0 && arr[j] > key) {
      arr[j + 1] = arr[j];
      j--;
    }

    arr[j + 1] = key;
  }

  return arr;
}

console.log(insertionSort([20, 5, 15, 35, 10]));
