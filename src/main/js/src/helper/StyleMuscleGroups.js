
function styleMuscleGroups(muscleGroups) {
    let res = "";
    let muscleGroupsSorted = muscleGroups.sort((x, y) => y.volume - x.volume);
    for(let i = 0; i < muscleGroupsSorted.length; i++) {
        if(i !== 0) {res += " | "}
        res += muscleGroupsSorted[i].name;
    }
    return res;
}

export default styleMuscleGroups;