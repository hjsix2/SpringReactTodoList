var gulp = require('gulp');
var babel = require('gulp-babel');
var concat = require('gulp-concat');

var sources = {
    jsx: [
        'app/main.jsx'
    ],
    libsJs: [
        'bower_components/react/react-with-addons.js',
        'bower_components/react/react-dom.js'
    ],
    css: [
        'bower_components/bootstrap/dist/css/bootstrap.css'
    ],
    html: [
        'app/index.html'
    ]
};

gulp.task('copy-html', function () {
    return gulp.src(sources.html)
        .pipe(gulp.dest('../public/'));
});

gulp.task('copy-and-concat-css', function () {
    return gulp.src(sources.css)
        .pipe(concat('styles.css'))
        .pipe(gulp.dest('../public/'));
});

gulp.task('copy-and-concat-libs', function () {
    return gulp.src(sources.libsJs)
        .pipe(concat('libs.js'))
        .pipe(gulp.dest('../public/'));
});

gulp.task('babel', function () {
    return gulp.src(sources.jsx)
        .pipe(babel({
            presets: ['react'],
            compact: false
        }))
        .pipe(concat('app.js'))
        .pipe(gulp.dest('../public'));
});

gulp.task('default', ['copy-html', 'copy-and-concat-css', 'copy-and-concat-libs', 'babel']);
gulp.task('dist', ['default']);
