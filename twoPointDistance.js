const euclideanDistance = (a, b) =>
  Math.hypot(...Object.keys(a).map((k) => b[k] - a[k]));

euclideanDistance([1, 1], [2, 3]);
