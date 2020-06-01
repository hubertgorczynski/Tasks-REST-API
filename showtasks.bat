call runcrud.bat
if "%ERRORLEVEL%" == "0" goto getTasks
echo.
echo Problems occurred during script processing.
goto fail

:getTasks
start chrome "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto end
echo.
echo There were problems with opening Your browser.
goto fail

:fail
echo.
echo There were problems.

:end
echo.
echo Work is finished.
