function fib(n) {
  const result = [0, 1];

  for (let i = 2; i <= n; i++) {
    let a = result[i - 1];
    let b = result[i - 2];

    result.push(a + b);
  }

  return result[n];
}

console.log(fib(7));
