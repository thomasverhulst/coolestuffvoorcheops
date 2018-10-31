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

  $.SubNav = function(element) {
    this.element = (element instanceof $) ? element : $(element);
    this.link = this.element.find('.nav-link');
    this.nav = this.element.find('.nav-sub');

    this.InitSubNav();
  };

  $.SubNav.prototype = {
    InitSubNav: function() {
      this.link.on('click', this.ToggleHandler.bind(this));
    },
    ToggleHandler: function(e) {
      e.preventDefault();

      this.nav.stop().slideToggle();

      // Changing FA5 icons with JS must be achieved with vanilla JS. jQuery will not work.
      let chevron = document.querySelector('.js-has-nav-sub > .nav-link svg');
      if (chevron.classList.contains('fa-chevron-down')){
        chevron.classList.add('fa-chevron-up');
        chevron.classList.remove('fa-chevron-down');
      } else {
        chevron.classList.add('fa-chevron-down');
        chevron.classList.remove('fa-chevron-up');
      }
    }
  };

  $.ChangePassword = function(element) {
    this.element = (element instanceof $) ? element : $(element);
    this.group = $('.js-change-password-group');

    this.InitChangePassword();
  }

  $.ChangePassword.prototype = {
    InitChangePassword: function() {
      this.group.hide();
      this.element.on('click', this.ToggleHandler.bind(this));
    },
    ToggleHandler: function(e) {
      if($(e.currentTarget).is(':checked')) {
        this.group.show();
        this.group.find('input').prop('required', true);
      } else {
        this.group.hide();
        this.group.find('input').prop('required', false)
      }
    }
  };

  $.Select2 = function(element) {
    this.element = (element instanceof $) ? element : $(element);

    this.InitSelect2();
  };

  $.Select2.prototype = {
    InitSelect2: function() {
      let $this = this;

      this.element.select2({
        placeholder: $this.element.data('placeholder'),
        //containerCssClass: [$this.element.attr('required'), 'form-control']
      });

      this.element.next('.select2-container').addClass($this.element.attr('required'));
      this.element.next('.select2-container').attr("id", 'select2-' + $this.element.attr('data-select2-id'));
    }
  };

  // Initalize
  new $.MobileMenu($('.header--middle'));
  new $.QuickMenu($('.header--right '));
  new $.QuickActions($('.icon-quick-menu ~ .o-card-hover'));
  new $.SubNav($('.js-has-nav-sub'));
  new $.ChangePassword($('.js-change-password'));
  new $.Select2($('.js-select-basic'));


  // Form validation.
  $.validator.setDefaults({
    errorClass: 'is-invalid',
    validClass: 'is-valid',
    highlight: function (element, errorClass, validClass) {
      if ($(element).hasClass('select2-hidden-accessible')) {
        $("#select2-" + $(element).attr('id')).removeClass(validClass).addClass(errorClass);
      } else {
        $(element).addClass(errorClass);
      }
    },
    unhighlight: function (element, errorClass, validClass) {
      if ($(element).hasClass('select2-hidden-accessible')) {
        console.log($(element).attr('id'));
        $("#select2-" + $(element).attr('id')).removeClass(errorClass).addClass(validClass);
      } else {
        $(element).removeClass(errorClass).addClass(validClass);
      }
    }
  });

  $('.js-needs-validation').each(function(e) {
    $(this).validate({
      ignore: 'input[type=hidden]',
      errorPlacement: function(error,element) {
        return true;
      }
    });
  });

  $('.select2-hidden-accessible').on('change', function() {
    $(this).trigger('blur');
  });

})(jQuery);
