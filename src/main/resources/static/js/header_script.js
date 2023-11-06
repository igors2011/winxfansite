document.getElementById("open_menu").addEventListener("click", openCloseMenu, false)

function openCloseMenu() {
    var menu = document.getElementById("menu_list");
    if (menu.style.display != "flex") {
        menu.style.display = "flex";
    }
    else {
        menu.style.display = "none";
    }
}