console.log('started');

let player=document.getElementById('player');
let rect = player.getBoundingClientRect();
// console.log(rect.height)
// console.log(rect.top)
// console.log(rect.width)
// console.log(rect.y)
// console.log(rect.x)
window.scroll(0,rect.top-(rect.y/2));

document.addEventListener('keydown', function(event) {
    event.preventDefault();
    document.getElementById('key').value=event.code;
    document.getElementById('key-form').submit();
});
