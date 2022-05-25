const arithmeticProgression = (n, lim) =>
  Array.from({ length: Math.ceil(lim / n) }, (_, i) => (i + 1) * n);

arithmeticProgression(5, 25); // [5, 10, 15, 20, 25]
