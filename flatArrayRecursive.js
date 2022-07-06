let arr = [
    [1,2],
    [3,4],
    [5,6,[7,8],9],
    [10,11,12]
]

// built in ->  arr.flat(2)

// Recusivly flatting korar niyom
function recurFlat(arr,depth=1) {
    let res = []
    arr.forEach((ar) => {
        if (Array.isArray(ar) && depth > 0) {
            res.push(...recurFlat(ar,depth-1))
        }else res.push(ar)
    })
    return res
}

recurFlat(arr,2)
