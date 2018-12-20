!function () {
    // body...
    window.fn = {};
    fn.snow = function(options) {
        console.log('qq')
        var defaults = {
            minSize: 5,
            maxSize: 20,
            newOn: 200,
            flakeSpeed: 10
        }

        var option = options || defaults;

        var hash = ['A', 'B', 'C', 'D', 'E', 'F'];

        var documentWidth = document.documentElement.clientWidth;
        var documentHeight = document.documentElement.clientHeight;

        console.log('kkk');
        var interval = setInterval(function(){
            var flake = document.createElement("div");
            var flakeSize = option.minSize + Math.random() * (option.maxSize - option.minSize);
            var flakeX = Math.random() * documentWidth - flakeSize;
            // var flakeY = Math.random() * documentHeight;

            var opacity = Math.random() + 0.2;
            var flakeColor = getColor();

            flake.style.cssText = "border-radius:50%;position:absolute;box-shadow:5px 5px 5px rgba(20, 20, 20, 0.5);height:" + flakeSize + "px;width:"
                                + flakeSize + "px;background:" + flakeColor + ";opacity" + opacity + ";left:" + flakeX + "px;";
            document.body.appendChild(flake);
            animate(flake, option.flakeSpeed, flakeSize);
        }, option.newOn);
        function animate(obj, speed, flakeSize) {
            var top = 0;
            var timer = setInterval(function() {
                top += speed;
                if (top + flakeSize > documentHeight) {
                    clearInterval(timer);

                    obj.parentNode.removeChild(obj);
                }
                obj.style.top = top + "px"; 
            },13);
        }

        function getColor() {
            var color = '#';
            for (var i = 0; i < 6; i++) {
                var tmp = Math.floor(Math.random() * 16 + 1);
                if (tmp > 9) {
                    tmp = hash[tmp - 10];
                }
                color += tmp;
            }
            // console.log(color);
            return color;
        }
    }
}();