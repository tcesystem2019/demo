use Modern::Perl "2010";
use Bitcoin::Crypto qw(btc_extprv btc_pub);
use Test::More;
use Data::Dumper;

use Bitcoin::Crypto::Key::ExtPrivate;

# extended keys are used for mnemonic generation and key derivation
my $mnemonic = Bitcoin::Crypto::Key::ExtPrivate->generate_mnemonic();
say "your mnemonic code is: $mnemonic";


my $master_key = Bitcoin::Crypto::Key::ExtPrivate->from_mnemonic($mnemonic);
my $derived_key = $master_key->derive_key("m/0'");

# basic keys are used for signatures and addresses
my $priv = $derived_key->get_basic_key();
my $pub = $priv->get_public_key();



say "private key: " . $priv->to_wif();
say "public key: " . $pub->to_hex();
say "address: " . $pub->get_segwit_address();


my $message = "Hello CPAN";
my $signature = $priv->sign_message($message);

if ($pub->verify_message($message, $signature)) {
    say "successfully signed message '$message'";
    say "signature: " . unpack "H*", $signature;
}
