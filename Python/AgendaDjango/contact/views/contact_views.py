from django.shortcuts import get_object_or_404, render
from contact.models import Contact
# from django.http import Http404

def index(request):
    contacts = Contact.objects.filter(show=True).order_by('-id')

    print(contacts.query)

    context = {
        'contacts': contacts,
        'site_title': 'Contatos - '
    }

    return render(
        request,
        'contact/index.html',
        context
    )

def contact(request, contact_id):
    # single_contact = Contact.objects.filter(pk=contact_id).first()

    # if single_contact is None:
    #     raise Http404()

    single_contact = get_object_or_404(Contact, pk=contact_id, show=True)

    site_title = f'{single_contact.first_name} {single_contact.last_name} - '

    # print(single_contact.query)

    context = {
        'contact': single_contact,
        'site_title': site_title
    }

    return render(
        request,
        'contact/contact.html',
        context
    )
