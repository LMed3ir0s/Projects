from django.shortcuts import get_object_or_404, render
from contact.models import Contact
from django.http import Http404

def index(request):
    contacts = Contact.objects.filter(show=True).order_by('-id')

    print(contacts.query)

    context = {
        'contacts': contacts,
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

    print(single_contact.query)

    context = {
        'contact': single_contact,
    }

    return render(
        request,
        'contact/contact.html',
        context
    )
