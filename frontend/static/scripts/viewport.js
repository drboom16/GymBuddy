(() => {
    const setAppHeight = () => {
        const height = window.visualViewport ? window.visualViewport.height : window.innerHeight;
        document.documentElement.style.setProperty("--app-height", `${height}px`);
    };

    setAppHeight();
    window.addEventListener("resize", setAppHeight);
    window.addEventListener("orientationchange", setAppHeight);

    if (window.visualViewport) {
        window.visualViewport.addEventListener("resize", setAppHeight);
        window.visualViewport.addEventListener("scroll", setAppHeight);
    }
})();
