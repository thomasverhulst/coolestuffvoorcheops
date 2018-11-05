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

  $.Select2 = function(element) {
    this.element = (element instanceof $) ? element : $(element);

    this.InitSelect2();
  };

  $.Select2.prototype = {
    InitSelect2: function() {
      let $this = this;

      this.element.select2({
        placeholder: $this.element.data('placeholder'),
      });

      this.element.next('.select2-container').addClass($this.element.attr('required'));
      this.element.next('.select2-container').attr("id", 'select2-' + $this.element.attr('data-select2-id'));
    }
  };

  $.Modal = function(element) {
    this.element = (element instanceof $) ? element : $(element);
    this.body = $('body');
    this.target = $(this.element.attr('data-target'));
    this.close = $(this.target).find('[data-dismiss="modal"]');
    this.confirm = $(this.target).find('[data-confirm="modal"]');

    this.InitModal();
  };

  $.Modal.prototype = {
    InitModal: function() {
      this.element.on('click', this.ModalHandler.bind(this));
      this.close.on('click', this.ModalClose.bind(this));
      this.confirm.on('click', this.ModalConfirm.bind(this));
    },
    ModalHandler: function(e) {
      e.preventDefault()
      $this = this;
      this.url = $(e.currentTarget).attr('href');
      this.target.find('.modal-title').text($(e.currentTarget).attr('data-modal-title'));
      this.target.show();
      this.body.append('<div class="modal-backdrop fade"></div>');
      this.backdrop = $('.modal-backdrop');

      window.setTimeout(function(){
        $this.target.addClass('show');
        $this.backdrop.addClass('show');
      }, 100);
    },
    ModalClose: function() {
      $this = this;
      this.target.removeClass('show');
      this.backdrop.removeClass('show');

      this.target.on('transitionend', function(e) {
        $(this).hide().unbind(e);
        $this.backdrop.remove();
      });
    },
    ModalConfirm: function() {
      window.location = this.url;
    }
  };

  // Initalize
  new $.MobileMenu($('.header--middle'));
  new $.QuickMenu($('.header--right '));
  new $.QuickActions($('.icon-quick-menu ~ .o-card-hover'));
  new $.SubNav($('.js-has-nav-sub'));
  new $.Select2($('.js-select-basic'));
  new $.Modal($('[data-toggle="modal"]'));

  // Form validation.
  $.validator.setDefaults({
    errorClass: 'invalid-feedback',
    errorElement: 'div',
    highlight: function (element, errorClass, validClass) {
      if ($(element).hasClass('select2-hidden-accessible')) {
        $("#select2-" + $(element).attr('id')).removeClass('is-valid').addClass('is-invalid');
      } else {
        $(element).addClass('is-invalid');
      }
    },
    unhighlight: function (element, errorClass, validClass) {
      if ($(element).hasClass('select2-hidden-accessible')) {
        $("#select2-" + $(element).attr('id')).removeClass('is-invalid').addClass('is-valid');
      } else {
        $(element).removeClass('is-invalid').addClass('is-valid');
      }
    }
  });

  $('.js-needs-validation').each(function(e) {
    $(this).validate({
      ignore: 'input[type=hidden]',
      rules : {
        passwordMatch: {
          equalTo: '#password'
        }
      },
      errorPlacement: function(error, e) {
        e.parents('.form-group').append(error);
      }
    });
  });

  $('.select2-hidden-accessible').on('change', function() {
    $(this).trigger('blur');
  });

})(jQuery);
