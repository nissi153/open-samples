const canvas = document.getElementById('game-board');
const ctx = canvas.getContext('2d');
const scale = 20;
const rows = canvas.height / scale;
const columns = canvas.width / scale;

let snake;
let score = 0;

function updateScore() {
    const scoreElement = document.getElementById('score');
    scoreElement.innerText = `Score: ${score}`;
}

(function setup() {
    snake = new Snake();
    fruit = new Fruit();

    fruit.pickLocation();

    window.setInterval(() => {
       
        ctx.fillStyle = "#000"; // 캔버스 배경색을 검은색으로 설정
        ctx.fillRect(0, 0, canvas.width, canvas.height); // 전체 캔버스 영역을 채웁니다.
        
        //ctx.clearRect(0, 0, canvas.width, canvas.height);

        fruit.draw();
        snake.update();
        snake.draw();

        if (snake.eat(fruit)) {
            fruit.pickLocation();
            score++; // 과일을 먹을 때마다 점수 증가
            updateScore(); // 점수를 업데이트하여 출력
        }

        snake.checkCollision();
    }, 150);
}());

window.addEventListener('keydown', ((event) => {
    const direction = event.key.replace('Arrow', '');
    snake.changeDirection(direction);
}));

function Snake() {
    this.x = 0;
    this.y = 0;
    this.xSpeed = scale * 1;
    this.ySpeed = 0;
    this.total = 1;
    this.tail = [];

    this.draw = function() {
        for (let i = 0; i < this.tail.length; i++) {
            ctx.fillStyle = '#00FF00'; // 초록색으로 몸통 색상 변경
            ctx.fillRect(this.tail[i].x, this.tail[i].y, scale, scale);
        }

        ctx.fillStyle = '#0000FF'; // 파란색으로 머리 색상 변경
        ctx.fillRect(this.x, this.y, scale, scale);
    }

    this.update = function() {
        for (let i = 0; i < this.tail.length - 1; i++) {
            this.tail[i] = this.tail[i + 1];
        }

        if (this.total > 0) {
            this.tail[this.total - 1] = { x: this.x, y: this.y };
        }

        this.x += this.xSpeed;
        this.y += this.ySpeed;

        if (this.x > canvas.width) {
            this.x = 0;
        }

        if (this.y > canvas.height) {
            this.y = 0;
        }

        if (this.x < 0) {
            this.x = canvas.width;
        }

        if (this.y < 0) {
            this.y = canvas.height;
        }
    }
    this.currentDirection = 'Right';

    this.changeDirection = function (direction) {
        let newDirection;

        switch (direction) {
            case 'Up':
                if (this.currentDirection !== 'Down') {
                    this.xSpeed = 0;
                    this.ySpeed = -scale;
                    newDirection = 'Up';
                }
                break;
            case 'Down':
                if (this.currentDirection !== 'Up') {
                    this.xSpeed = 0;
                    this.ySpeed = scale;
                    newDirection = 'Down';
                }
                break;
            case 'Left':
                if (this.currentDirection !== 'Right') {
                    this.xSpeed = -scale;
                    this.ySpeed = 0;
                    newDirection = 'Left';
                }
                break;
            case 'Right':
                if (this.currentDirection !== 'Left') {
                    this.xSpeed = scale;
                    this.ySpeed = 0;
                    newDirection = 'Right';
                }
                break;
        }

        if (newDirection) {
            this.currentDirection = newDirection;
        }
    }
    
    this.eat = function(fruit) {
        if (this.x === fruit.x && this.y === fruit.y) {
            this.total++;
            return true;
        }
    
        return false;
    }
    
    this.checkCollision = function() {
        for (let i = 0; i < this.tail.length; i++) {
            if (this.x === this.tail[i].x && this.y === this.tail[i].y) {
                this.total = 0;
                this.tail = [];
            }
        }
    }
    }
    
    function Fruit() {
    this.x;
    this.y;
    
    this.pickLocation = function() {
        this.x = (Math.floor(Math.random() * rows - 1) + 1) * scale;
        this.y = (Math.floor(Math.random() * columns - 1) + 1) * scale;
    }
    
    this.draw = function() {
        ctx.fillStyle = '#ff0000';
        ctx.fillRect(this.x, this.y, scale, scale);
    }
}
