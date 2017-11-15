jQuery(document).ready(function($) {
	$('#illustration')
        .click(function() {
            $(this).addClass('expand');
        }).mouseleave(function() {
            $(this).removeClass('expand');
        });

    $(document).ready(function() {
        function showReturnDate() {
            $('#returnDate').show();
            $('#departureDate').removeClass('col-md-6').addClass('col-md-3');
        }

        function hideReturnDate() {
            $('#returnDate').hide();
            $('#departureDate').removeClass('col-md-3').addClass('col-md-6');
        }

        $('#searchForm\\:ticketType\\:0').change(hideReturnDate);
        $('#searchForm\\:ticketType\\:1').change(showReturnDate);

        $('#searchForm\\:ticketType input[type="radio"]:checked').change();

        $('#departureDate .datepicker').datepicker({
            minDate : 0,
            dateFormat: 'dd/mm/yy',
            onSelect: function(selectedDate) {
                $('#returnDate .datepicker').datepicker('option', 'minDate', selectedDate);
            }
        });

        $('#returnDate .datepicker').datepicker({
			minDate : 0,
            dateFormat: 'dd/mm/yy',
            onSelect: function( selectedDate ) {
                $('#departureDate .datepicker').datepicker('option', 'maxDate', selectedDate);
            }
        });
    });
});