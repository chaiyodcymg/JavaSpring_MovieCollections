function resizeMenu() {
  let wrapW = $("#horizontal-nav .menu-wrap").width(),
    menuW = $("#horizontal-nav .menu").width();

  let itemsToScroll = 3,
    widthToScroll = 0,
    scrollX = parseFloat($("#horizontal-nav .menu-wrap .menu").css("left"));

  if ($(this).hasClass("btn-prev")) {
    let prevItemIndex,
      prevItemsWidth = 0;

    $("#horizontal-nav .list-item").each((i, el) => {
      if (prevItemIndex !== undefined) return;
      prevItemsWidth += $(el).outerWidth() + 14;
      if (Math.ceil(prevItemsWidth) > Math.abs(scrollX)) prevItemIndex = i;
    });

    for (
      let i = prevItemIndex;
      i >= 0 && i > prevItemIndex - itemsToScroll;
      i--
    )
      prevItemsWidth -=
        $(`#horizontal-nav .list-item:eq(${i})`).outerWidth() + 14;

    widthToScroll = scrollX - prevItemsWidth;
    let newScrollX = Math.abs(scrollX) + widthToScroll;
    $("#horizontal-nav .menu-wrap .menu").css({ left: newScrollX });

    $(this).toggleClass("hidden", !Math.floor(newScrollX));
    $(".btn-next").removeClass("hidden");
  } else {
    let nextItemIndex,
      prevItemsWidth = 0;

    $("#horizontal-nav .list-item").each((i, el) => {
      if (nextItemIndex !== undefined) return;
      prevItemsWidth += $(el).outerWidth() + 14;
      if (Math.floor(prevItemsWidth - 14) > Math.abs(scrollX) + wrapW)
        nextItemIndex = i;
    });

    if (scrollX + wrapW >= menuW) {
      if (!$(this).hasClass("hidden")) $(this).addClass("hidden");
      return;
    }
    $(this).removeClass("hidden");

    for (
      let i = nextItemIndex + 1;
      i < nextItemIndex + itemsToScroll &&
      nextItemIndex + itemsToScroll <= $("#horizontal-nav .list-item").length;
      i++
    )
      prevItemsWidth +=
        $(`#horizontal-nav .list-item:eq(${i})`).outerWidth() + 14;
    widthToScroll = prevItemsWidth - 14 - (Math.abs(scrollX) + wrapW);
    let newScrollX = scrollX - widthToScroll;
    $("#horizontal-nav .menu-wrap .menu").css({ left: newScrollX });
    console.log(Math.round(Math.abs(newScrollX + wrapW)), menuW);
    $(this).toggleClass(
      "hidden",
      Math.round(Math.abs(newScrollX) + wrapW) >= Math.round(menuW)
    );
    $(".btn-prev").removeClass("hidden");
  }
}
$(() => {
  $("#horizontal-nav .list-item").each(function () {
    if ($(this).find(".sub-menu").length) $(this).addClass("has-submenu");
  });
  $("#horizontal-nav").on("click", ".btn-prev, .btn-next", resizeMenu);

  $(document).on("resize", resizeMenu);
});
