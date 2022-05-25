export function likesBy(names) {
  if (names.length === 1) {
    return `${names[0].split(" ")[0]} likes this`;
  } else if (names.length === 2) {
    return `${names[0].split(" ")[0]} and ${names[1].split(" ")[0]} likes this`;
  } else if (names.length === 3) {
    return `${names[0].split(" ")[0]}, ${names[1].split(" ")[0]} and ${
      names[2]
    } like this`;
  } else if (names.length === 0) {
    return "no one likes this";
  } else {
    return `${names[0].split(" ")[0]}, ${names[1].split(" ")[0]} and ${
      names.length - 2
    } others like this`;
  }
}
