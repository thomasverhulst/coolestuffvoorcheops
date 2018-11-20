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
        placeholder: $this.element.data('placeholder')
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

  $.Tabs = function(element) {
    this.element = (element instanceof $) ? element : $(element);
    this.active = this.element.find('.o-tabs-group--item.active');
    this.previous = this.element.find('[data-toggle="previous"]');
    this.next = this.element.find('[data-toggle="next"]');
    this.panes = this.element.find('[data-content="panes"]');
    this.activePane = this.element.find('.o-tabs-group--pane.active');
    this.tab = this.element.find('[data-toggle="tab"]');

    this.InitTabs();
  };

  $.Tabs.prototype = {
    InitTabs: function() {
      this.previous.on('click', this.PreviousHandler.bind(this));
      this.next.on('click', this.NextHandler.bind(this));
      this.tab.on('click', this.TabHandler.bind(this));
    },
    PreviousHandler: function(e) {
      e.preventDefault();

      if (this.active.prev('.o-tabs-group--item').length !== 0) {
        this.MoveTab('prev');
        this.MovePane(this.active.attr('href'));
      }
    },
    NextHandler: function(e) {
      e.preventDefault();

      if (this.active.next('.o-tabs-group--item').length !== 0) {
        this.MoveTab('next');
        this.MovePane(this.active.attr('href'));
      }
    },
    TabHandler: function(e) {
      e.preventDefault();
      this.MovePane($(e.currentTarget).attr('href'));
      this.active.removeClass('show active').attr('aria-selected', false);
      this.active = $(e.currentTarget).addClass('show active').attr('aria-selected', true);
    },
    MoveTab: function(position) {
      this.active.removeClass('active');
      this.active = position === 'next' ? this.active.next('.o-tabs-group--item').addClass('active') : this.active.prev('.o-tabs-group--item').addClass('active');
    },
    MovePane: function(pane) {
      $this = this;
      this.activePane.hide().removeClass('show active');
      this.activePane = this.panes.find(pane).show();

      window.setTimeout(function(){
        $this.activePane.addClass('show active');
      }, 100);
    }
  };

  $.FileInput = function(element) {
    this.element = (element instanceof $) ? element : $(element);
    this.upload = this.element.siblings('.a-form-input-file--upload');
    this.download = this.element.closest('.row').prev().find('.icon-download-file');

    this.InitFileInput();
  };

  $.FileInput.prototype = {
    InitFileInput: function() {
      this.element.on('change', this.FileInputHandler.bind(this));
    },
    FileInputHandler: function(e) {
      let filename = e.target.value.split('\\').pop();

      if(filename) {
        this.download.hide();
        this.upload.text(filename);
        this.upload.css('display', 'inline-block');
      } else {
        this.download.show();
        this.upload.text('');
        this.upload.hide();
      }
    }
  };

  $.AddRow = function(element) {
    this.element = (element instanceof $) ? element : $(element);

    this.InitAddRow();
  };

  $.AddRow.prototype = {
    InitAddRow: function() {
      $(document).on('click', '.js-add-row', this.AddRowHandler.bind(this));
    },
    AddRowHandler: function(e) {
      e.preventDefault();
      let target = $(e.currentTarget);

      $.post('/' + target.attr('data-action'), function(returnData) {
        $(target.attr('data-object')).replaceWith(returnData);

        if (target.attr('data-action') === "addSkillTechnology") {
          setAutocompleteTechnologies();
        }
      });
    }
  };

  $.RemoveRow = function(element) {
    this.element = (element instanceof $) ? element : $(element);

    this.InitRemoveRow();
  };

  $.RemoveRow.prototype = {
    InitRemoveRow: function() {
      $(document).on('click', '.js-remove-row', this.RemoveRowHandler.bind(this));
    },
    RemoveRowHandler: function(e) {
      e.preventDefault();
      let target = $(e.currentTarget);

      $.post('/' + target.attr('data-action'), 'removeItem=' + target.attr('data-value'), function(returnData) {
        $(target.attr('data-object')).replaceWith(returnData);

        if (target.attr('data-action') === "addSkillTechnology") {
          setAutocompleteTechnologies();
        }
      });
    }
  };

  // $.SetCurrentSalary = function(element) {
  //   this.element = (element instanceof $) ? element : $(element);
  //   this.icon = this.element.find('.icon');
  //   this.reset = this.element.find('.svg-inline--fa');
  //
  //   this.InitSetCurrentSalary();
  // };
  //
  // $.SetCurrentSalary.prototype = {
  //   InitSetCurrentSalary: function() {
  //     this.element.on('click', this.SetCurrentSalaryRowHandler.bind(this));
  //   },
  //   SetCurrentSalaryRowHandler: function(e) {
  //     e.preventDefault();
  //     let $this = this;
  //
  //     if (!this.element.hasClass('reset')) {
  //       let fields = ['grosssalary', 'car', 'dailyallowance', 'mealvouchers', 'hospitalization', 'groupinsurance'];
  //       this.icon.hide();
  //       this.element.find('.svg-inline--fa').removeClass('d-none');
  //       this.element.addClass('reset');
  //
  //       $.each(fields, function(i, field) {
  //         let proposalField = $('#proposal_' + field + '_' + $this.element.attr('data-value'));
  //         let currentField = $('#current_' + field);
  //
  //         if (proposalField.attr('type') === 'checkbox') {
  //           if (proposalField.val() === true) {
  //             currentField.prop('checked', true);
  //           } else {
  //             currentField.prop('checked', false);
  //           }
  //         } else {
  //           currentField.val(proposalField.val());
  //         }
  //       });
  //     } else {
  //       $.post('/' + $(this.element).attr('data-action'), function(returnData) {
  //         $($this.element.attr('data-object')).replaceWith(returnData);
  //         $this.icon.show();
  //         $this.element.find('.svg-inline--fa').addClass('d-none');
  //         $this.element.removeClass('reset');
  //       });
  //     }
  //   }
  // };

  // Initalize
  new $.MobileMenu($('.header--middle'));
  new $.QuickMenu($('.header--right '));
  new $.QuickActions($('.icon-quick-menu ~ .o-card-hover'));
  new $.SubNav($('.js-has-nav-sub'));
  new $.Select2($('.js-select-basic'));
  new $.Modal($('[data-toggle="modal"]'));
  new $.Tabs($('.o-tabs-group'));
  new $.FileInput($('.a-form-input-file'));
  new $.AddRow('.js-add-row');
  new $.RemoveRow('.js-remove-row');

  // $.each($('.js-set-current-salary'), function(i, item) {
  //   new $.SetCurrentSalary(item);
  // });

  // Autocomplete.
  $('.js-autocomplete-countries').autocomplete({
    serviceUrl: '/countries',
    paramName: 'str',
    maxHeight: 150,
    transformResult: function(response) {
      return {
        suggestions: $.map(JSON.parse(response), function(dataItem) {
          return { value: dataItem.name, data: dataItem.code };
        })
      };
    }
  });

  setAutocompleteTechnologies();

  function setAutocompleteTechnologies() {
    $('.js-autocomplete-technologies').autocomplete({
      serviceUrl: '/technologies',
      paramName: 'str',
      maxHeight: 150,
      transformResult: function(response) {
        return {
          suggestions: $.map(JSON.parse(response), function(dataItem) {
            return { value: dataItem.name, data: dataItem.id };
          })
        };
      }
    });
  }

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

  $.validator.addMethod('filesize', function (value, element, param) {
    return this.optional(element) || (element.files[0].size <= param)
  }, 'Bestandsgrootte mag niet meer dan 10MB zijn.');

  $('.js-needs-validation').each(function(e) {
    $(this).validate({
      ignore: 'input[type=hidden]',
      rules : {
        passwordMatch: {
          equalTo: '#password'
        },
        email: {
          email: true
        },
        file: {
          extension: "doc?x|xls?x|txt|pdf|rtf|eml",
          filesize: 10000000
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
