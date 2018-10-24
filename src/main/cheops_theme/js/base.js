(function ($) {
  $.MobileMenu = function (element) {
    this.element = (element instanceof $) ? element : $(element);
    this.hamburger = $('.js-hamburger');

    this.InitMobileMenu();
  };

  $.MobileMenu.prototype = {
    InitMobileMenu: function () {
      this.hamburger.on('click', this.ToggleHandler.bind(this));
    },
    ToggleHandler: function(e) {
      e.preventDefault();

      this.hamburger.toggleClass('active');
      this.element.stop().slideToggle();
    }
  };

  $.QuickMenu = function (element) {
    this.element = (element instanceof $) ? element : $(element);
    this.dots = $('.js-dots');

    this.InitQuickMenu();
  };

  $.QuickMenu.prototype = {
    InitQuickMenu: function () {
      this.dots.on('click', this.ToggleHandler.bind(this));
    },
    ToggleHandler: function(e) {
      e.preventDefault();

      this.dots.toggleClass('active');
      this.element.stop().slideToggle();
    }
  };

  $.QuickActions = function(element) {
    this.element = (element instanceof $) ? element : $(element);
    this.quickMenu = $('.icon-quick-menu');

    this.InitQuickActions();
  };

  $.QuickActions.prototype = {
    InitQuickActions: function() {
      this.quickMenu.on('click', this.ToggleHandler.bind(this));
    },
    ToggleHandler: function(e) {
      e.preventDefault();

      let left = 0;
      if (this.quickMenu[0].getBoundingClientRect().left > this.element.width() / 2) {
        left = (-this.element.width() / 2) + (this.quickMenu.width()/2);
      } else {
        left = -this.quickMenu[0].getBoundingClientRect().left + 10;
        this.element.find('.card--arrow').css({
          'left': this.quickMenu[0].getBoundingClientRect().left - 10
        });
      }

      this.quickMenu.toggleClass('active');
      this.element.css({
        'position': 'absolute',
        'top': 'calc(100% + 20px)',
        'left': left
      });

      this.element.toggle();
    }
  };

  // Initalize
  new $.MobileMenu($('.header--middle'));
  new $.QuickMenu($('.header--right '));
  new $.QuickActions($('.icon-quick-menu ~ .o-card-hover'));

  // Form validation.
  $('.js-needs-validation').each(function(e) {
    $(this).submit(function(ev) {
      if ($(this)[0].checkValidity() === false) {
        ev.preventDefault();
        ev.stopPropagation();
      }
      $(this).addClass('was-validated');
    });
  });

})(jQuery);
